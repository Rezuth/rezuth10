
include emu8086.inc

org 100h
          
          
mov cx,n
          
firstloop:

    mov aux,cx   ; save cx
    mov cx,n
    lea si,array
    mov bx,si            
    print 'array:'
               
    secondloop:
        mov ax,[si]
        cmp ax,[bx]
            jle nothing
        mov bx,si
        
        nothing:
        
            call PRINT_NUM
            cmp cx,1
                je alterloop
            print ','
            
            alterloop:
            
                add si,2
                       
    loop secondloop
    
    printn
    print 'The maximum of the array is:'
    mov ax,[bx]
    call PRINT_NUM
    printn
    push ax
    mov [bx],-9999 ;value for replacing the maximum values
    mov cx,aux ;restore cx

loop firstloop
           
printn 
mov cx,n          
print 'array sorted:' 

sorted:
          
    pop ax
    call PRINT_NUM
    cmp cx,1
        je alterloop2
    print ','
            
    alterloop2:
            
        add si,2
    
loop sorted

ret

n dw 6
array dw 21,4,-10,-5,7,40
aux dw 0

DEFINE_PRINT_NUM
DEFINE_PRINT_NUM_UNS

end
