import random

def matriu(m, n):
    matriu = []
    fila = []

    for i in range(m):
        for j in range(n):
            fila.append(random.randint(-100, 100))
        matriu.append(fila)
        fila = []


    print("Número de files: " + str(m))
    print("Número de columnes: " + str(n))
    print(matriu)


matriu(3, 4)