import random

def matriu(m, n):
    matriu = []
    fila = []

    for i in range(m):
        for j in range(n):
            fila.append(random.randint(-100, 100))
        matriu.append(fila)
        fila = []

    return matriu


def sum_mat(mat_a, mat_b):
    matriu_resultant = []
    fila_resultant = []
    files = len(mat_a)
    columnes = len(mat_a[0])

    for i in range(files):
        for j in range(columnes):
            fila_resultant.append(mat_a[i][j] + mat_b[i][j])
        matriu_resultant.append(fila_resultant)
        fila_resultant = []

    return matriu_resultant


m = int(input("Introdueix un número per a les files de la matriu."))
n = int(input("Introdueix un número per a les columnes de la matriu."))
matriu_1 = matriu(m, n)
matriu_2 = matriu(m, n)
print("Primera matriu: " + str(matriu_1) + "\nSegona matriu: " + str(matriu_2))
print("Resultat de sumar les dues matrius: " + str(sum_mat(matriu_1, matriu_2)))

