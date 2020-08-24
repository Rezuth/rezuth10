
include emu8086.inc

org 100h

mov cx,n            
lea si,array
mov bx,[si]

print 'The maximum in the array:'
           
start:

    mov ax,[si]
    cmp ax,bx
        jle nothing
    mov bx,ax
    
    nothing:
   
        add si,2
                   
loop start

mov ax,bx
call PRINT_NUM

ret

n dw 6
array dw 2,4,20,-6,9,-12

DEFINE_PRINT_NUM
DEFINE_PRINT_NUM_UNS

end

