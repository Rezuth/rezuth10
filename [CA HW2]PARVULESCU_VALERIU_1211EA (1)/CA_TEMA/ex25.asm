
include emu8086.inc

org 100h

mov cx,n
lea si,array


print 'sum of the elements in the array is:'

addision:

      mov al,[si]
      add sum,al   
      inc si
    
loop addision

mov al,sum
call PRINT_NUM



ret

n dw 7
sum db 0
array db 74,6,-120,8,-4,20,21 ;sum is 5

DEFINE_PRINT_NUM
DEFINE_PRINT_NUM_UNS

end
