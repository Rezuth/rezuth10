
include emu8086.inc

;let's assume the string has length 12
;the 18th doesn't let the user choose a string
;with length greater than 12
;for the string 'elefaccafele' the program
;prints 'Palindrome!'
;for the string 'elefaccafelx' the program
;prints 'Not Palindrome!'

org 100h

mov di,s
mov dx,13 ;string size
print 'the string is:'
call GET_STRING
printn
mov cx,12 
lea si,di
print 'the string is:'
loop1:

    mov bl,[si] 
    push bx 
    inc si  
loop loop1

mov cx,12
lea si,di
        
compare:

    pop bx
    cmp bl,[si]
        je nothing
    mov ok,1
    
    nothing:
        
        inc si
        
loop compare    


cmp ok,0
    je goodresult
print 'Not palindrome!'
jmp finish

goodresult:

    print 'Palindrome!'        


finish:
              
ret

ok dw 0 
s dw 'xxxxxxxxxxxx'

DEFINE_GET_STRING

end 



