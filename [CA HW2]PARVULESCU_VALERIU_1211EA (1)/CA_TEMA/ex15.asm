
;We will use the interrupt:INT 21h / AH=6-direct console input or output. 
;We will display the string 'Salutare!' by reading character with character from the console

org 100h

    mov ah, 6
	mov dl, 'S'
	int 21h       ; output character. 

	mov ah, 6
	mov dl, 255  ;display the character
	int 21h
	
	mov ah, 6
	mov dl, 'a'
	int 21h        

	mov ah, 6
	mov dl, 255
	int 21h
	
	mov ah, 6
	mov dl, 'l'
	int 21h       

	mov ah, 6
	mov dl, 255
	int 21h
	
	mov ah, 6
	mov dl, 'u'
	int 21h        

	mov ah, 6
	mov dl, 255
	int 21h
	
	mov ah, 6
	mov dl, 't'
	int 21h        

	mov ah, 6
	mov dl, 255
	int 21h
	
	mov ah, 6
	mov dl, 'a'
	int 21h       

	mov ah, 6
	mov dl, 255
	int 21h
	
	mov ah, 6
	mov dl, 'r'
	int 21h        

	mov ah, 6
	mov dl, 255
	int 21h
	
	mov ah, 6
	mov dl, 'e'
	int 21h        

	mov ah, 6
	mov dl, 255
	int 21h
	
	mov ah, 6
	mov dl, '!'
	int 21h        

	mov ah, 6
	mov dl, 255
	int 21h  
  
ret




