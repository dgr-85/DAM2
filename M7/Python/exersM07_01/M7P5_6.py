import math
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
        raise ValueError("Aquesta matriu pot tenir un màxim de " + str(max_k) + " valors diferents de 0.")

    matriu_resultant = {}

    i = 0
    while i < k:
        posicio = ((random.randint(0, m-1)), (random.randint(0, n-1)))
        matriu_resultant[posicio] = 1
        i += 1

    return matriu_resultant


def distancies(dic_pos):
    dic_resultant = {}

    for ciutat_1 in dic_pos:
        dic_resultant[ciutat_1] = {}
        (x1, y1) = dic_pos[ciutat_1]

        for ciutat_2 in dic_pos:
            if ciutat_1 != ciutat_2:
                (x2, y2) = dic_pos[ciutat_2]
                dic_resultant[ciutat_1][ciutat_2] = int(math.sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2)))

    return dic_resultant




