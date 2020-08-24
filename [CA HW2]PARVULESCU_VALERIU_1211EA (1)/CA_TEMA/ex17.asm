
include emu8086.inc

;let's assume the string has maximum length 32

org 100h

mov di,s
mov dx,33 ;string size
print 'the string is:'
call GET_STRING
printn 
lea si,di
print 'the number of characters in the string is:'

loop1:

    inc count
    inc si
    cmp [si],0 ;check if the string is finished
        je finish   
    jmp loop1                 

finish:

    mov ax,count
    call PRINT_NUM  
              
ret
 
s dw 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'
count dw 0

DEFINE_GET_STRING
DEFINE_PRINT_NUM
DEFINE_PRINT_NUM_UNS

end 