
include emu8086.inc

;let's assume the string has length 8

org 100h
   
print 'get string from keyboard with length maximum 8:'
mov di,s     
mov dx,9;string size.
call GET_STRING    
printn
print 'printed string is:'
lea si,di 
call PRINT_STRING

ret

s dw 'xxxxxxxx'

DEFINE_GET_STRING   
DEFINE_PRINT_STRING

end





