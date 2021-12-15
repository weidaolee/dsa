import numpy as np

def compute_bitmap_size (n:int, p:float) -> int:
    """
    n: number of data
    p: tolerance of erro rate
    """
    return int(-n * np.log2(np.e) * np.log2(p) + 1)


def compute_k (n:int, m:int) -> int:
    """
    n: number of data
    m: bitmap size
    """
    return int(np.log(2) * (m / n) + 1)

def compute_real_p (n:int, m:int) -> float:
    """
    m: real memory size
    """
    k = int(np.log(2) * (m / n) + 1)
    p = (1 - np.e ** -(n/m * k)) ** k
    return p


if __name__ == '__main__':
    n = 2 ** 30
    p = 0.0001

    print(compute_bitmap_size(n, p))
    print(-n * np.log(p) / np.log(2) ** 2)
