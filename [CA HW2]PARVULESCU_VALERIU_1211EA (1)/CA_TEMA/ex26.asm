
include emu8086.inc

org 100h

mov cx,n
lea si,array


print 'sum of the elements in the array is:'

addision:

      mov ax,[si]
      add sum,ax   
      add si,2
    
loop addision

mov ax,sum
call PRINT_NUM



ret

n dw 6
sum dw 0
array dw 12,-87,18,4,3,-25 ;sum is -75

DEFINE_PRINT_NUM
DEFINE_PRINT_NUM_UNS

end


