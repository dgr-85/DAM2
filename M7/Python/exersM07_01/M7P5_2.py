def dec_a_hex(txt_dec):
    dec = abs(int(txt_dec))
    digits = "0123456789abcdef"
    base_hex = 16
    res = []

    while dec >= base_hex:
        res.insert(0, str(digits[dec % 16]))
        dec = dec // 16
    res.insert(0, str(digits[dec]))
    return ''.join(res)


numString = "1995"
print("El valor absolut del número " + numString + " passat a hexadecimal és: " + dec_a_hex(numString))
