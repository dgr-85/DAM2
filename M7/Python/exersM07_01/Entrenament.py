import random

# P1


def zeros_seguits(txt):
    num = 0
    maxim = 0

    for i in range(len(txt)):
        if txt[i] == '0':
            maxim += 1
        else:
            if num < maxim:
                num = maxim
            maxim = 0

    return num


# P2


def moviment():
    punts_cardinals = ('N', 'S', 'E', 'O')
    punt_cardinal = input("Introdueix una direcció.").upper()
    passes = int(input("Introdueix el número de passes."))

    if punt_cardinal in punts_cardinals and passes in range(1, 50):
        txt_num = punt_cardinal + str(passes)
        return txt_num
    else:
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

