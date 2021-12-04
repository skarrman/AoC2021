from functools import reduce
from operator import mul
from os import environ

def mark(board, val, bingo=False):
    for i in range(5):
        mcol, mrow = True, True
        for j in range(5):
            board[i][j], board[j][i] = -1 if board[i][j] == val else board[i][j], -1 if board[j][i] == val else board[j][i]
            mcol, mrow = mcol and board[j][i] == -1, mrow and board[i][j] == -1
        bingo = bingo or mcol or mrow
    return bingo

def getSolutionPart1(numbers, boards):
    for number in numbers:
        for board in boards:
            if mark(board, number):
                return sum([sum(map(lambda num: num if num != -1 else 0, row))for row in board]) * number

def getSolutionPart2(numbers, boards):
    for number in numbers:
        for board in boards:
            if mark(board, number):
                if len(boards) == 1:
                    return sum([sum(map(lambda num: num if num != -1 else 0, row))for row in board]) * number
                boards.remove(board)

with open('input.txt') as f:
    numbers = list(map(int, f.readline().split(',')))
    f.readline()
    boards = [[[int(num) for num in row.split(" ") if num != ""] for row in board.split("\n")] for board in f.read().split("\n\n")]

print((getSolutionPart2(numbers, boards) if environ.get('part') == 'part2' else getSolutionPart1(numbers, boards)))
