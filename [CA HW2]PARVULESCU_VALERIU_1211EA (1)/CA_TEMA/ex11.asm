
include emu8086.inc

org 100h

mov dx,n ; dx is a counter for first loop
          
firstloop:

    mov cx,n    
    lea si,array
    mov ok,0   
    secondloop:
    
       mov ax,[si]
       cmp ax,[si+2]
            jle nothing 
       mov ok,1
       mov bx,[si+2]
       mov [si+2],ax
       mov [si],bx
       
       nothing:
       
            add si,2
        
    loop secondloop
    
cmp ok,0
    je finish
dec dx  
cmp dx,0  
    jg firstloop

finish:

    mov cx,n
    lea si,array
    print 'array sorted:'
    sorted:   

        mov ax,[si]
        call PRINT_NUM
        cmp cx,1
            je alterprint
        print ','
   
        alterprint:
        
            add si,2
    
    loop sorted



ret

n dw 8
ok dw 0
array dw 14,-25,6,10,-5,26,-101,40

DEFINE_PRINT_NUM
DEFINE_PRINT_NUM_UNS

end



