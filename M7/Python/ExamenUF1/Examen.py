import random


# P1


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
        passes = float(passes)
        if passes >= -50 and passes <=  50 and punt_cardinal in punts_cardinals:
            return txt_mov
        else:
            return None
    except ValueError:
        return None


# P2


def abreujar(txt):
    txt_resultant = ""
    vocals = ('a', 'e', 'i', 'o', 'u')
    primera = True

    for lletra in txt:

        if lletra.lower() in vocals and primera is False:
            lletra = ''

        if lletra.lower() in vocals and primera is True:
            primera = False

        if lletra == ' ':
            primera = True

        txt_resultant += lletra

    return txt_resultant


# P3


def acces(contrasenyes, usuari, contrasenya):

    if usuari not in contrasenyes:
        return "Usuari inexistent"

    if contrasenyes[usuari]["intents"] >= 3:
        return "Usuari bloquejat"

    if contrasenya != contrasenyes[usuari]["contrasenya"]:
        contrasenyes[usuari]["intents"] += 1
        return "Contrasenya incorrecta"

    contrasenyes[usuari]["intents"] = 0
    return "Accés concedit"


# P4


def ronda(parelles_ronda):
    guanyadors = []
    parelles_ronda_següent = []

    for parella in parelles_ronda:
        guanyadors.append(parella[random.randint(0, 1)])

    i = 0
    while i < len(guanyadors):
        [x, y] = [guanyadors[i], guanyadors[i+1]]
        parelles_ronda_següent.append([x, y])
        i += 2

    return parelles_ronda_següent


# P5


def triangle(n):
    llista_de_llistes = [[1], [1, 1]]

    for fil in range(n):
        proxim = [[1]]
        for col in llista_de_llistes:
            ultim = llista_de_llistes[-1]
            for num in range(1, len(ultim)):
                proxim.append(ultim[num])
            proxim.append(1)
        llista_de_llistes.append(proxim)

    return llista_de_llistes

print(triangle(3))