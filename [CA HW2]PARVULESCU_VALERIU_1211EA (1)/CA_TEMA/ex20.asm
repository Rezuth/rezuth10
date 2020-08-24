
include emu8086.inc

org 100h
   
print 'n='
call SCAN_NUM
mov n,cx
           
printn

print 'The nth fibonacci number is:'
 
cmp n,1
   je found1
cmp n,2
   je found2
sub n,2 
 
 
search:

   

    mov ax,var1
    add ax,var2
    dec n
    cmp n,0
        je finish
    mov bx,var2
    mov var1,bx
    mov var2,ax
    jmp search
       
finish:

    call PRINT_NUM
    jmp over
    
found1:

    print '0'
    jmp over
    
found2:

    print '1' ;no need to jump to over here
    
over:        

ret
   
n dw ?
var1 dw 0
var2 dw 1

DEFINE_SCAN_NUM   
DEFINE_PRINT_NUM
DEFINE_PRINT_NUM_UNS
  
end