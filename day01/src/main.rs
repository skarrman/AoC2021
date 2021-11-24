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

fn _is_prime(n: usize, i: usize) -> bool {
    match n {
        n if n <= 2 => n == 2,
        n if n % i == 0 => false,
        n if i * i > n => true,
        _ => _is_prime(n, i + 1),
    }
}
fn is_prime(n: usize) -> bool {
    _is_prime(n, 2)
}

fn part1(data: &Vec<usize>) {
    let res = data.iter().enumerate().fold(0, |sum, (i, num)| {
        sum + if is_prime(*num) { i * num } else { 0 }
    });
    println!("{}", res);
}

fn part2(data: &Vec<usize>) {
    let res = data.iter().enumerate().fold(0i32, |sum, (i, num)| {
        sum + match is_prime(*num) {
            false if i % 2 == 0 => *num as i32,
            false => -(*num as i32),
            _ => 0,
        }
    });
    println!("{}", res);
}
