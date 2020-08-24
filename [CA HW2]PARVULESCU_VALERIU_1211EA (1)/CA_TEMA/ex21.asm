
include emu8086.inc


org 100h
   
print 'n='
call SCAN_NUM
mov n,cx                 

printn

print 'n! is:'
            
cmp n,0
    je res1
cmp n,1
    je res2
    
mov ax,1
mov bx,1
     
power:

    mul bx
    dec n
    cmp n,0
        je finish
    inc bx
    jmp power
       
finish:

    call PRINT_NUM
    jmp over
    
res1:

    print '1'
    jmp over
    
res2:

    print '1'            ; no need to jump to over here
    
    
over:        

ret
   
n dw ?

DEFINE_SCAN_NUM   
DEFINE_PRINT_NUM
DEFINE_PRINT_NUM_UNS
  
end