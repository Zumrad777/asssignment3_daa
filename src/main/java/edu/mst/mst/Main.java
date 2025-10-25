package edu.mst.mst;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.mst.mst.algo.Kruskal;
import edu.mst.mst.algo.Prim;
import edu.mst.mst.model.*;
import edu.mst.mst.io.ReportWriter;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ObjectMapper om = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        InputStream in = Main.class.getResourceAsStream("/ass_3_input.json");
        if (in == null) {
            System.err.println(" ERROR: ass_3_input.json не найден в src/main/resources/");
            return;
        }
        GraphInput gi = om.readValue(in, GraphInput.class);

        List<RunResult> results = new ArrayList<>();
        for (GraphCase g : gi.graphs) {
            System.out.println("▶ Graph " + g.id + " (|V|=" + g.nodes.size() + ", |E|=" + g.edges.size() + ")");

            Prim.Result pr = Prim.run(g.nodes, g.edges);
            Kruskal.Result kr = Kruskal.run(g.nodes, g.edges);

            Map<String, Integer> stats = new LinkedHashMap<>();
            stats.put("vertices", g.nodes.size());
            stats.put("edges", g.edges.size());

            AlgorithmResult prim = new AlgorithmResult(pr.edges, pr.cost, pr.ops, pr.ms);
            AlgorithmResult kruskal = new AlgorithmResult(kr.edges, kr.cost, kr.ops, kr.ms);

            results.add(new RunResult(g.id, stats, prim, kruskal));

            boolean match = Math.abs(pr.cost - kr.cost) < 1e-9;
            System.out.printf(" Graph %d: Prim=%.3f (ops=%d, %.3fms), Kruskal=%.3f (ops=%d, %.3fms), match=%s%n",
                    g.id, pr.cost, pr.ops, pr.ms, kr.cost, kr.ops, kr.ms, match);
        }


        Path outJson = Path.of("ass_3_output_generated.json");
        ReportWriter.writeJson(results, outJson);

        Path outMd = Path.of("ass_3_report.md");
        ReportWriter.writeMarkdown(results, outMd);

        System.out.println("\n Сохранено:");
        System.out.println(" - " + outJson.toAbsolutePath());
        System.out.println(" - " + outMd.toAbsolutePath());
    }
}
