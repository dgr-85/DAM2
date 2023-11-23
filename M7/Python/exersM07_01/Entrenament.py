import random

# P1 (corregit)


def zeros_seguits(txt):
    num = 0
    maxim = 0

    for car in txt:
        if car == '0':
            maxim += 1
            if num < maxim:
                num = maxim
        else:
            maxim = 0

    return num


# P2 (corregit)


def moviment():
    punts_cardinals = ('N', 'S', 'E', 'O')
    txt_mov = input("Introdueix una direcció i un número de passes.").upper()
    punt_cardinal = ""
    passes = ""
    for car in txt_mov:
        if punt_cardinal == "":
            punt_cardinal = car
        else:
            passes += car
    try:
        passes = int(passes)
        if passes in range(1, 50) and punt_cardinal in punts_cardinals:
            return txt_mov
        else:
            return None
    except ValueError:
        return None


#  P3


def lotto_6_49():
    llista_num = []
    i = 0

    while i < 6:
        num = random.randint(1, 49)
        if num not in llista_num:
            llista_num.append(num)
            i += 1

    return llista_num


# P4


def mes_repetits(llista):
    llista_mes_repetits = []
    diccionari = {}
    maxim = 1

    for valor in llista:
        if valor not in diccionari:
            diccionari[valor] = 1
        else:
            diccionari[valor] += 1
            if maxim < diccionari[valor]:
                maxim = diccionari[valor]

    for clau in diccionari:
        if diccionari[clau] == maxim:
            llista_mes_repetits.append(clau)

    return llista_mes_repetits

