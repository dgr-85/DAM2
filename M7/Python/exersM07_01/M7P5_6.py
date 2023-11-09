import random


def sum_mat(mat_a, mat_b):
    matriu_resultant = {}

    for posicio in mat_a:
        if posicio in mat_b:
            matriu_resultant[posicio] = mat_a[posicio] + mat_b[posicio]
        else:
            matriu_resultant[posicio] = mat_a[posicio]

    for posicio in mat_b:
        if posicio not in mat_a:
            matriu_resultant[posicio] = mat_b[posicio]

    return matriu_resultant


def matriu(m, n, k):
    max_k = int(m * n * 0.1)
    if k > max_k:
        raise ValueError("Aquesta matriu pot tenir un màxim de " + str(max_k) + " valors iguals a 1.")

    matriu_resultant = {}

    for x in range(m):
        for y in range(n):
            matriu_resultant[(x, y)] = 0

    i = 0
    while i < k:
        posicio = ((random.randint(0, m-1)), (random.randint(0, n-1)))
        if matriu_resultant[posicio] != 1:
            matriu_resultant[posicio] = 1
            i += 1

    return matriu_resultant


matriu_exemple = matriu(44, 44, 194)
print(matriu_exemple)
