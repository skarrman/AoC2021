use std::env;

fn main() {
    let out_dir = env::var("part");
    match out_dir {
        Ok(part) if part == "part1" => part1(),
        Ok(part) if part == "part2" => part1(),
        _ => {
            part1();
            part2()
        }
    }
}

fn part1() {
    println!("Part1");
}

fn part2() {
    println!("Part2");
}
