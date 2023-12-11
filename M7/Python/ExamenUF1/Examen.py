import random


# P1


def moviment():
    punts_cardinals = "NSEO"
    txt_mov = input("Introdueix una direcció i un número de passes.").upper()
    punt_cardinal = txt_mov[0:1]
    passes = txt_mov[1:]

    try:
        passes = float(passes)
        if -50 <= passes <= 50 and len(punt_cardinal) > 0 and punt_cardinal in punts_cardinals:
            return txt_mov
        else:
            return None
    except ValueError:
        return None


# P2


def abreujar(txt):
    txt_resultant = ""
    vocals = "aeiou"
    primera = True

    for lletra in txt:

        if lletra.lower() in vocals and not primera:
            lletra = ''

        elif lletra.lower() in vocals and primera:
            primera = False

        elif lletra == ' ':
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
    llista_de_llistes = []

    for fil in range(n):
        proxim = []
        for col in range(fil+1):
            if col == 0 or col >= fil:
                proxim.append(1)
            else:
                proxim.append(llista_de_llistes[fil-1][col-1] + llista_de_llistes[fil-1][col])

        llista_de_llistes.append(proxim)

    return llista_de_llistes


