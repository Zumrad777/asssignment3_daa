package edu.mst.mst.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mst.mst.model.AlgorithmResult;
import edu.mst.mst.model.Edge;
import edu.mst.mst.model.RunResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReportWriter {
    public static void writeJson(List<RunResult> results, Path outJson) throws IOException {
        ObjectMapper om = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        var root = new java.util.HashMap<String, Object>();
        root.put("results", results);
        om.writeValue(outJson.toFile(), root);
    }

    public static void writeMarkdown(List<RunResult> results, Path outMd) throws IOException {
        StringBuilder md = new StringBuilder();
        md.append("# Assignment 3: Optimization of a City Transportation Network (MST)\n\n");
        md.append("## Input Summary\n");
        md.append("Loaded ").append(results.size()).append(" graph(s) from `ass_3_input.json`.\n\n");

        for (RunResult r : results) {
            md.append("### Graph ").append(r.graph_id).append("\n");
            md.append("- **Vertices:** ").append(r.input_stats.get("vertices"))
                    .append(", **Edges:** ").append(r.input_stats.get("edges")).append("\n\n");

            md.append("**Prim's MST** — total cost: **").append(r.prim.total_cost)
                    .append("**, ops: **").append(r.prim.operations_count)
                    .append("**, time: **").append(String.format("%.3f", r.prim.execution_time_ms)).append(" ms**\n\n");
            md.append(edgesTableMd(r.prim.mst_edges)).append("\n");

            md.append("**Kruskal's MST** — total cost: **").append(r.kruskal.total_cost)
                    .append("**, ops: **").append(r.kruskal.operations_count)
                    .append("**, time: **").append(String.format("%.3f", r.kruskal.execution_time_ms)).append(" ms**\n\n");
            md.append(edgesTableMd(r.kruskal.mst_edges)).append("\n");

            boolean match = Math.abs(r.prim.total_cost - r.kruskal.total_cost) < 1e-9;
            md.append("**MST Cost Match:** ").append(match ? " (identical total cost)" : " (mismatch)").append("\n\n");
        }

        md.append("## Comparison & Analysis\n");
        md.append("- **Prim** good for dense graphs; **Kruskal** efficient for sparse.\n");
        md.append("- Operation counts show relative efficiency.\n\n");

        md.append("## References\n");
        md.append("- Cormen, Leiserson, Rivest, Stein. *Introduction to Algorithms*.\n");
        md.append("- Tarjan (1975). Union–Find efficiency.\n");

        Files.writeString(outMd, md.toString());
    }

    private static String edgesTableMd(List<Edge> list) {
        if (list == null || list.isEmpty()) return "_(no edges)_\n";
        StringBuilder sb = new StringBuilder();
        sb.append("| From | To | Weight |\n|---|---:|---:|\n");
        for (Edge e : list) {
            sb.append("| ").append(e.from).append(" | ").append(e.to).append(" | ").append(e.weight).append(" |\n");
        }
        return sb.toString();
    }
}
