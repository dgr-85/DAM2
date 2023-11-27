def triangles(txt):
    # txt = txt.replace(' ', '')
    inici = 0
    fi = 1
    pos = 1
    creix = True
    resultat = ""

    while fi < len(txt):
        resultat += txt[inici:fi] + "\n"
        inici = fi

        if pos > 1 and creix is False:
            pos -= 2

        if pos < 7 and creix is True:
            pos += 2
            if pos == 7:
                creix = False

        if pos == 1:
            creix = True

        fi += pos

    if txt[fi - pos:] != "":
        resultat += txt[fi - pos:]

    return resultat


print(triangles("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam vitae nunc consectetur, fringilla purus et, faucibus tellus. Integer in eros dapibus, viverra nunc a, ornare nisl. In accumsan, odio at vehicula pellentesque, metus enim tristique eros, id aliquam tellus justo eget felis. Duis lobortis venenatis est vestibulum congue. Etiam vitae vehicula felis, vulputate consectetur mi. Aliquam vel congue sem. Duis pretium pretium sapien at semper. Sed in neque sed lorem porta ultricies. Vestibulum ultrices diam sed ex varius dictum."))
