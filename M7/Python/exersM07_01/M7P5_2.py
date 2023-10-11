def dec_a_hex(txt_dec):
    dec = int(txt_dec)
    if dec < 16:
        return txt_dec
    else:
        res = []
        while dec >= 16:
            res.insert(0, str(dec % 16))
            dec = dec//16
        res.insert(0, str(dec))
        for i in range(len(res)):
            match res[i]:
                case '10':
                    res[i] = 'A'
                case '11':
                    res[i] = 'B'
                case '12':
                    res[i] = 'C'
                case '13':
                    res[i] = 'D'
                case '14':
                    res[i] = 'E'
                case '15':
                    res[i] = 'F'
                case _:
                    pass
        return ''.join(res)


numString = "11"
print("El número " + numString + " passat a hexadecimal és: " + dec_a_hex(numString))
