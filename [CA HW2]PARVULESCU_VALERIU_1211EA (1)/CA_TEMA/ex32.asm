
include emu8086.inc

org 100h

mov di,s
mov dx,33 ;string size
print 'Enter your name:'
call GET_STRING
lea si,di
printn
print 'Welcome '
call PRINT_STRING
printn ' to the Maths quiz!'
printn
printn '1.The addision exercise!'
printn 'Choose the correct answer:(14+81=?)'
printn '1.-100 ; 2.95 ; 3.64'
call SCAN_NUM
mov n,cx
compare:

    cmp n,1
        je print1
    cmp n,2
        je print2
    cmp n,3
        je print3
    
print1:

    printn 'Wrong!Try again!'
    sub score,50
    call SCAN_NUM
    mov n,cx
    jmp compare
    
print2:

    printn 'Good job!'
    printn
    add score,50
    jmp next
    
print3:

    printn 'Wrong!Try again!'
    sub score,50
    call SCAN_NUM
    mov n,cx
    jmp compare
    
next:

    printn '2.The substraction exercise!'
    printn 'Choose the correct answer:(74-137=?)'
    printn '1.-63 ; 2.2 ; 3.44'
    call SCAN_NUM
    mov n,cx
    compare2:

        cmp n,1
            je print11
        cmp n,2
            je print21
        cmp n,3
            je print31
            
    print11:

        printn 'Good job!'
        printn
        add score,50
        jmp next2
    
    print21:

        printn 'Wrong!Try again!'
        sub score,50
        call SCAN_NUM
        mov n,cx
        jmp compare2
    
    print31:

        printn 'Wrong!Try again!'
        sub score,50
        call SCAN_NUM
        mov n,cx
        jmp compare2
        
next2:

    printn '3.The multiplication exercise!'
    printn 'Choose the correct answer:(14x25=?)'
    printn '1.240 ; 2.128 ; 3.350'
    call SCAN_NUM
    mov n,cx
    compare3:

        cmp n,1
            je print111
        cmp n,2
            je print211
        cmp n,3
            je print311
            
    print111:

        printn 'Wrong!Try again!'
        sub score,50
        call SCAN_NUM
        mov n,cx
        jmp compare3
    
    print211:

        printn 'Wrong!Try again!'
        sub score,50
        call SCAN_NUM
        mov n,cx
        jmp compare3
    
    print311:

        printn 'Good job!'
        printn
        add score,50
        jmp next3
        
next3:
    
    printn 'Last exercise!Good job so far!'
    printn
    printn '3.The division exercise!'
    printn 'Choose the correct answer:(13/7=?)'
    printn '1.25,remainder 34 ; 2.1,remainder 6 ; 3.12,remainder 0'
    call SCAN_NUM
    mov n,cx
    compare4:

        cmp n,1
            je print1111
        cmp n,2
            je print2111
        cmp n,3
            je print3111
            
    print1111:

        printn 'Wrong!Try again!'
        sub score,50
        call SCAN_NUM
        mov n,cx
        jmp compare4
    
    print2111:

        printn 'Good job!'
        printn
        add score,50
        jmp finish
    
    print3111:

        printn 'Wrong!Try again!'
        sub score,50
        call SCAN_NUM
        mov n,cx
        jmp compare4
        
finish:

    printn
    printn 'Well done!You finished the Maths quiz!'
    printn
    print 'final score:'
    mov ax,score
    call PRINT_NUM

ret

s dw 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'
n dw ?
score dw 0 


DEFINE_GET_STRING
DEFINE_SCAN_NUM
DEFINE_PRINT_NUM
DEFINE_PRINT_NUM_UNS
DEFINE_PRINT_STRING  

end
