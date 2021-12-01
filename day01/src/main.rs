use std::env;
use std::fs;

fn get_data() -> Vec<usize> {
    let path = "input.txt";
    let contents = fs::read_to_string(path).expect("Could not read file");
    contents
        .split("\n")
        .map(|n| n.parse::<usize>().unwrap())
        .collect()
}

fn main() {
    let data = get_data();
    let out_dir = env::var("part");
    match out_dir {
        Ok(part) if part == "part1" => part1(&data),
        Ok(part) if part == "part2" => part1(&data),
        _ => {
            part1(&data);
            part2(&data);
        }
    }
}

fn part1(data: &Vec<usize>) {
    let res = data.as_slice().windows(2).fold(0, |sum, window| {
        sum + if window[0] < window[1] { 1 } else { 0 }
    });
    println!("{}", res);
}

fn part2(data: &Vec<usize>) {
    let res = data
        .as_slice()
        .windows(3)
        .map(|window| window[0] + window[1] + window[2])
        .collect::<Vec<usize>>()
        .windows(2)
        .fold(0, |sum, window| {
            sum + if window[0] < window[1] { 1 } else { 0 }
        });
    println!("{}", res);
}
