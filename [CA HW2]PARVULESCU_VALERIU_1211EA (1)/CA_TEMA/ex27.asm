
include emu8086.inc

org 100h

lea si,array1
lea bx,array2
lea di,array3
mov cx,5

addision:
       
    mov ax,[si]
    add ax,[bx]
    mov [di],ax
    add si,2
    add bx,2
    add di,2
   
loop addision

lea si,array3
mov cx,5

print 'sum of arrays is:'

finish:

    mov ax,[si]
    call PRINT_NUM
    cmp cx,1
        je alterprint
    print ','
    
    alterprint:
    
        add si,2
        
loop finish

ret

array1 dw 1,6,5,-7,9
array2 dw 3,4,-8,2,15
array3 dw ?,?,?,?,?

DEFINE_PRINT_NUM
DEFINE_PRINT_NUM_UNS

end



