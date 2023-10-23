def dec_a_hex(txt_dec):
    dec = abs(int(txt_dec))
    digits = "0123456789abcdef"
    base_hex = 16
    res = ''

    while dec >= base_hex:
        res = str(digits[dec % 16]) + res
        dec = dec // 16
    res = str(digits[dec]) + res
    return res


numString = "77"
print("El valor absolut del número " + numString + " passat a hexadecimal és: " + dec_a_hex(numString))
