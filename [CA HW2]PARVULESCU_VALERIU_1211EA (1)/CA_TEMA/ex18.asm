
include emu8086.inc

;let's assume the string has maximum length 32
;for the string SALUTARe! it will display 7

org 100h

mov di,s
mov dx,33 ;string size
print 'the string is:'
call GET_STRING
printn 
lea si,di
print 'the number of uppercase characters in the string is:'
loop1:

    mov al,[si] 
    cmp al,65  ; char less than 'A',we don't care
        jl nothing
    cmp al,90 ;character greater than 'Z',we don't care
        jg nothing
    inc count
          
    nothing:
    
         inc si
         cmp [si],0
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


