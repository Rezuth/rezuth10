
include emu8086.inc 

org 100h


putc s[0]
putc s[1]
putc s[2]
putc s[3]
putc s[4]
putc s[5]
putc s[6]
putc s[7]
putc s[8]


ret

s db 'Salutare!'

end