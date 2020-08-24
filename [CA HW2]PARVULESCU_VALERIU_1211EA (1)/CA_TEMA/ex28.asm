
include emu8086.inc

;the program will do the desired operation
;for example:if the string is '12+34' it will print 46

org 100h

print 'string='

mov di,s
mov dx,6 ;string size
call GET_STRING
printn
lea si,di

print 'The result of the operation is:'

parse:
    
        mov al,[si]
        sub al,'0'
        mov bx,10
        mul bx           ;we form the numbers var1 and var2
        add var1,ax
        mov al,[si+1]
        sub al,'0'
        mov bx,1
        mul bx
        add var1,ax
        mov al,[si+3]
        sub al,'0'
        mov bx,10
        mul bx
        add var2,ax
        mov al,[si+4]
        sub al,'0'
        mov bx,1
        mul bx
        add var2,ax
        
cmp var2,0
    jne alright
mov al,[si+2]
cmp al,47
    je error

lea si,di
    
alright:

    mov al,[si+2]
    cmp al,43
        je addision
    cmp al,45
        je substraction
    cmp al,42
        je multiplication
    cmp al,47
        je division
        
    addision:
    
        mov ax,var1
        add ax,var2
        jmp finish
        
    substraction:
    
        mov ax,var1
        sub ax,var2
        jmp finish
        
    multiplication:
    
        mov ax,var1
        mov bx,var2
        mul bx
        jmp finish
        
    division:
    
        mov ax,var1
        mov bx,var2
        div bx
        jmp finish
        
finish:

    call PRINT_NUM
    jmp finish2
    
error:

    printn
    print 'Error! Division by 0 is impossible!'

finish2:                

ret

s dw 'xxxxx'
var1 dw 0
var2 dw 0
         
DEFINE_GET_STRING
DEFINE_PRINT_NUM
DEFINE_PRINT_NUM_UNS

end         



