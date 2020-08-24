
org 100h

;we use the INT 10h / AH = 0Ch interrupt,which changes the color for a single pixel

firstside:

    mov al, 13h
	mov ah, 0
	int 10h     ;set graphics mode
	mov al, 4h  ;set the color(which is red)
	mov cx, 110 ;column
	mov dx, 20  ;row
	mov ah, 0Ch
	int 10h     ;set pixel
	mov cx, 111
	mov dx, 21
	mov ah, 0Ch
	int 10h
	mov cx, 112
	mov dx, 22
	mov ah, 0Ch
	int 10h
	mov cx, 113
	mov dx, 23
	mov ah, 0Ch
	int 10h
	mov cx, 114
	mov dx, 24
	mov ah, 0Ch
	int 10h
	mov cx, 115
	mov dx, 25
	mov ah, 0Ch
	int 10h
	mov cx, 116
	mov dx, 26
	mov ah, 0Ch
	int 10h
	mov cx, 117
	mov dx, 27
	mov ah, 0Ch
	int 10h
	mov cx, 118
	mov dx, 28
	mov ah, 0Ch
	int 10h
	mov cx, 119
	mov dx, 29
	mov ah, 0Ch
	int 10h
	mov cx, 120
	mov dx, 30
	mov ah, 0Ch
	int 10h
	mov cx, 121
	mov dx, 31
	mov ah, 0Ch
	int 10h
	mov cx, 122
	mov dx, 32
	mov ah, 0Ch
	int 10h
	mov cx, 123
	mov dx, 33
	mov ah, 0Ch
	int 10h
	mov cx, 124
	mov dx, 34
	mov ah, 0Ch
	int 10h
	mov cx, 125
	mov dx, 35
	mov ah, 0Ch
	int 10h
	mov cx, 126
	mov dx, 36
	mov ah, 0Ch
	int 10h
	
secondside:

    mov cx, 125
	mov ah, 0Ch
	int 10h
	mov cx, 124
	mov ah, 0Ch
	int 10h
	mov cx, 123
	mov ah, 0Ch
	int 10h
	mov cx, 122
	mov ah, 0Ch
	int 10h
	mov cx, 121
	mov ah, 0Ch
	int 10h
	mov cx, 120
	mov ah, 0Ch
	int 10h
	mov cx, 119
	mov ah, 0Ch
	int 10h
	mov cx, 118
	mov ah, 0Ch
	int 10h
	mov cx, 117
	mov ah, 0Ch
	int 10h
	mov cx, 116
	mov ah, 0Ch
	int 10h
	mov cx, 115
	mov ah, 0Ch
	int 10h
	mov cx, 114
	mov ah, 0Ch
	int 10h
	mov cx, 113
	mov ah, 0Ch
	int 10h
	mov cx, 112
	mov ah, 0Ch
	int 10h
	mov cx, 111
	mov ah, 0Ch
	int 10h
	mov cx, 110
	mov ah, 0Ch
	int 10h
	mov cx, 109
	mov ah, 0Ch
	int 10h
	mov cx, 108
	mov ah, 0Ch
	int 10h
	mov cx, 107
	mov ah, 0Ch
	int 10h
	mov cx, 106
	mov ah, 0Ch
	int 10h
	mov cx, 105
	mov ah, 0Ch
	int 10h
	mov cx, 104
	mov ah, 0Ch
	int 10h
	mov cx, 103
	mov ah, 0Ch
	int 10h
	mov cx, 102
	mov ah, 0Ch
	int 10h
	mov cx, 101
	mov ah, 0Ch
	int 10h
	mov cx, 100
	mov ah, 0Ch
	int 10h
	mov cx, 99
	mov ah, 0Ch
	int 10h
	mov cx, 98
	mov ah, 0Ch
	int 10h
	mov cx, 97
	mov ah, 0Ch
	int 10h
	mov cx, 96
	mov ah, 0Ch
	int 10h
	mov cx, 95
	mov ah, 0Ch
	int 10h
	mov cx, 94
	mov ah, 0Ch
	int 10h
	mov cx, 93
	mov ah, 0Ch
	int 10h
	mov cx, 92
	mov ah, 0Ch
	int 10h

thirdside:                              

	mov cx, 93
	mov dx, 35
	mov ah, 0Ch
	int 10h
	mov cx, 94                     
	mov dx, 34
	mov ah, 0Ch
	int 10h
	mov cx, 95
	mov dx, 33
	mov ah, 0Ch
	int 10h
	mov cx, 96
	mov dx, 32
	mov ah, 0Ch
	int 10h
	mov cx, 97
	mov dx, 31
	mov ah, 0Ch
	int 10h
	mov cx, 98
	mov dx, 30
	mov ah, 0Ch
	int 10h
	mov cx, 99
	mov dx, 29
	mov ah, 0Ch
	int 10h
	mov cx, 100
	mov dx, 28
	mov ah, 0Ch
	int 10h
	mov cx, 101
	mov dx, 27
	mov ah, 0Ch
	int 10h
	mov cx, 102
	mov dx, 26
	mov ah, 0Ch
	int 10h
	mov cx, 103
	mov dx, 25
	mov ah, 0Ch
	int 10h
	mov cx, 104
	mov dx, 24
	mov ah, 0Ch
	int 10h
	mov cx, 105
	mov dx, 23
	mov ah, 0Ch
	int 10h
	mov cx, 106
	mov dx, 22
	mov ah, 0Ch
	int 10h
	mov cx, 107
	mov dx, 21
	mov ah, 0Ch
	int 10h
	mov cx, 108
	mov dx, 20
	mov ah, 0Ch
	int 10h
	mov cx, 109
	mov dx, 19
	mov ah, 0Ch
	int 10h
	    	     
ret
	 




