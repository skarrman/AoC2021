/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    private final List<String> input;

    public App(List<String> input) {
        this.input = input;
    }

    public Integer getSolutionPart1() {
        var sum = 0;
        for (var row : input) {
            for (var digit : row.split(" \\| ")[1].split(" ")) {
                switch (digit.replace(" ", "").length()) {
                    case 2, 3, 4, 7 -> sum++;
                }
            }
        }
        return sum;
    }

    private String intersection(String... input) {
        var intersection = new StringBuilder();
        for (var c : input[0].toCharArray()) {
            var contains = true;
            for (int i = 1; i < input.length; i++) {
                contains = contains && input[i].indexOf(c) != -1;
            }
            if (contains)
                intersection.append(c);
        }
        return intersection.toString();
    }

    private String inverseIntersection(String... input) {
        var inverseIntersection = new StringBuilder();
        for (var c : input[0].toCharArray()) {
            var contains = true;
            for (int i = 1; i < input.length; i++) {
                contains = contains && input[i].indexOf(c) == -1;
            }
            if (contains)
                inverseIntersection.append(c);
        }
        return inverseIntersection.toString();
    }

    public Integer getSolutionPart2() {
        var sum = 0;
        for (var row : input) {
            var mapping = new HashMap<Character, Character>();
            var five = new ArrayList<String>();
            var six = new ArrayList<String>();
            var known = new HashMap<Integer, String>();
            var parts = row.split(" \\| ");
            for (var digit : parts[0].split(" ")) {
                switch (digit.replace(" ", "").length()) {
                    case 2 -> known.put(1, digit);
                    case 3 -> known.put(7, digit);
                    case 4 -> known.put(4, digit);
                    case 5 -> five.add(digit);
                    case 6 -> six.add(digit);
                    case 7 -> known.put(8, digit);
                }
            }
            var a = inverseIntersection(known.get(7), known.get(1));
            var adg = intersection(five.get(0), five.get(1), five.get(2));
            var d = intersection(adg, known.get(4));
            var g = inverseIntersection(adg, a, d);
            var f = intersection(six.get(0), six.get(1), six.get(2), known.get(1));
            var c = inverseIntersection(known.get(1), f);
            var b = inverseIntersection(known.get(4), c, d, f);
            var e = inverseIntersection(known.get(8), a, b, c, d, f, g);
            mapping.put('a', a.charAt(0));
            mapping.put('b', b.charAt(0));
            mapping.put('c', c.charAt(0));
            mapping.put('d', d.charAt(0));
            mapping.put('e', e.charAt(0));
            mapping.put('f', f.charAt(0));
            mapping.put('g', g.charAt(0));
            var number = 0;
            for (var digit : parts[1].split(" ")) {
                number = number * 10 + switch (digit.replace(" ", "").length()) {
                    case 2 -> 1;
                    case 3 -> 7;
                    case 4 -> 4;
                    case 5 -> {
                        if (digit.indexOf(mapping.get('e')) != -1)
                            yield 2;
                        else if (digit.indexOf(mapping.get('c')) != -1)
                            yield 3;
                        else yield 5;
                    }
                    case 6 -> {
                        if (digit.indexOf(mapping.get('d')) == -1)
                            yield 0;
                        else if (digit.indexOf(mapping.get('e')) != -1)
                            yield 6;
                        else yield 9;
                    }
                    case 7 -> 8;
                    default -> throw new RuntimeException("digit not recognized");
                };
            }
            sum += number;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        List<String> input = parseInput("input.txt");
        String part = System.getenv("part") == null ? "part1" : System.getenv("part");
        if (part.equals("part2"))
            System.out.println(new App(input).getSolutionPart2());
        else
            System.out.println(new App(input).getSolutionPart1());
    }

    private static List<String> parseInput(String filename) throws IOException {
        return Files.lines(Path.of(filename))
                .collect(Collectors.toList());
    }
}
