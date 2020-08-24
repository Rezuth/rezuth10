
include emu8086.inc


org 100h
   
print 'm='
call SCAN_NUM
mov m,cx                 ;let's assume that n(the power of m) is a positive integer(>=0)

printn

print 'n='
call SCAN_NUM
mov n,cx

printn

print 'm to the power of n is:'
            
cmp n,0
    je res1
cmp n,1
    je res2
    
mov ax,m
mov bx,m
dec n
     
power:

    mul bx
    dec n
    cmp n,0
        je finish
    jmp power
       
finish:

    call PRINT_NUM
    jmp over
    
res1:

    print '1'
    jmp over
    
res2:

    mov ax,m            ; no need to jump to over here
    call PRINT_NUM
    
over:        

ret
   
m dw ?
n dw ?

DEFINE_SCAN_NUM   
DEFINE_PRINT_NUM
DEFINE_PRINT_NUM_UNS
  
end