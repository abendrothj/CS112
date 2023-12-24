import random
import time

# length of the list
Length = 10000


def set_values(values):
    # Assign random values
    for i in range(len(values)):
        values[i] = random.randint(0, 4 * Length)


def bubble_sort(values):
    # Do bubble sort
    for i in range(len(values) - 1, 0, -1):
        for j in range(1, i + 1):
            if values[j - 1] > values[j]:
                values[j - 1], values[j] = values[j], values[j - 1]


def main():
    # Create list of random values
    values = [0] * Length
    set_values(values)
    start_time = time.time()
    bubble_sort(values)
    # Check time, calculate difference, then print how long bubble sort took
    end_time = time.time()
    print(end_time - start_time)

    with open("sort.txt", "w") as file:
        for value in values:
            file.write(f"{value} ")


if __name__ == "__main__":
    main()
