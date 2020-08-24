import pygame
import random
import numpy
import pickle
from pynput.keyboard import Controller
 
pygame.font.init()
keyboard = Controller()
 
# GLOBALS VARS
s_width = 800
s_height = 700
play_width = 300
play_height = 600
block_size = 30
 
top_left_x = (s_width - play_width) // 2
top_left_y = s_height - play_height


class AiPlayer:
    def __init__(self):
        self.pieceW = random.uniform(-1.0, 1.0)
        self.rotationW = random.uniform(-1.0, 1.0)
        self.distW = random.uniform(-1.0, 1.0)*4
        self.distLeftW = random.uniform(-1.0, 1.0)
        self.pieceXW = random.uniform(-1.0, 1.0)*2
        
        self.fitness = -1
        
    def __str__(self):
        return '\nFitness: ' + str(self.fitness)

###### Genetic algorithm params ######
population = 130    
genomes = [AiPlayer() for _ in range (population)]
######################################
 
S = [['.....',
      '.....',
      '..00.',
      '.00..',
      '.....'],
     ['.....',
      '..0..',
      '..00.',
      '...0.',
      '.....']]
 
Z = [['.....',
      '.....',
      '.00..',
      '..00.',
      '.....'],
     ['.....',
      '..0..',
      '.00..',
      '.0...',
      '.....']]
 
I = [['..0..',
      '..0..',
      '..0..',
      '..0..',
      '.....'],
     ['.....',
      '0000.',
      '.....',
      '.....',
      '.....']]
 
O = [['.....',
      '.....',
      '.00..',
      '.00..',
      '.....']]
 
L = [['.....',
      '...0.',
      '.000.',
      '.....',
      '.....'],
     ['.....',
      '..0..',
      '..0..',
      '..00.',
      '.....'],
     ['.....',
      '.....',
      '.000.',
      '.0...',
      '.....'],
     ['.....',
      '.00..',
      '..0..',
      '..0..',
      '.....']]
 
T = [['.....',
      '..0..',
      '.000.',
      '.....',
      '.....'],
     ['.....',
      '..0..',
      '..00.',
      '..0..',
      '.....'],
     ['.....',
      '.....',
      '.000.',
      '..0..', 
      '.....'],
     ['.....',
      '..0..',
      '.00..',
      '..0..',
      '.....']]

PLAYER = [['.....',
          '.....',
          '..0..',
          '.....',
          '.....']]


 
shapes = [S, Z, I, O, L, T]
shape_colors = [(0, 255, 0), (255, 0, 0), (0, 255, 255), (255, 255, 0), (0, 0, 255), (128, 0, 128), (52, 237, 132)]
 
 
class Piece:
    rows = 20  # y
    columns = 10  # x
 
    def __init__(self, column, row, shape, piece = True):
        self.x = column
        self.y = row
        self.shape = shape
        if piece:
            self.color = shape_colors[shapes.index(shape)]
        else:
            self.color = (255, 255, 255)
        
        self.rotation = random.choice([e for e in range(0, 3)])
        
 
def create_grid(locked_positions={}):
    grid = [[(0,0,0) for x in range(10)] for x in range(20)]
 
    for i in range(len(grid)):
        for j in range(len(grid[i])):
            if (j,i) in locked_positions:
                c = locked_positions[(j,i)]
                grid[i][j] = c
    return grid
 
 
def convert_shape_format(shape):
    positions = []
    format = shape.shape[shape.rotation % len(shape.shape)]
 
    for i, line in enumerate(format):
        row = list(line)
        for j, column in enumerate(row):
            if column == '0':
                positions.append((shape.x + j, shape.y + i))
 
    for i, pos in enumerate(positions):
        positions[i] = (pos[0] - 2, pos[1] - 4)
 
    return positions
 
 
def valid_space(shape, grid):
    formatted = convert_shape_format(shape)
 
    for pos in formatted:
        if pos[1] > 23 or pos[0] > 9 or pos[0] < 0:
            return False;
 
    return True
 
 
def check_lost(current_piece, player):
    player = player[0]
    player = (player[0], player[1] + 2)
    for pos in current_piece:
        if player == pos:
            return True
    return False
 
 
def get_shape():
    global shapes, shape_colors
 
    return Piece(random.choice([e for e in range(1, 9)]), 0, random.choice(shapes))
 
 
def draw_text_middle(text, size, color, surface):
    font = pygame.font.SysFont('comicsans', size, bold=True)
    label = font.render(text, 1, color)
 
    surface.blit(label, (top_left_x + play_width/2 - (label.get_width() / 2), top_left_y + play_height/2 - label.get_height()/2))
 
 
def draw_grid(surface, row, col):
    sx = top_left_x
    sy = top_left_y
    for i in range(row):
        pygame.draw.line(surface, (128,128,128), (sx, sy+ i*30), (sx + play_width, sy + i * 30))  # horizontal lines
        for j in range(col):
            pygame.draw.line(surface, (128,128,128), (sx + j * 30, sy), (sx + j * 30, sy + play_height))  # vertical lines
  
 
def draw_next_shape(shape, surface):
    font = pygame.font.SysFont('comicsans', 30)
    label = font.render('Next Shape', 1, (255,255,255))
 
    sx = top_left_x + play_width + 50
    sy = top_left_y + play_height/2 - 100
    format = shape.shape[shape.rotation % len(shape.shape)]
 
    for i, line in enumerate(format):
        row = list(line)
        for j, column in enumerate(row):
            if column == '0':
                pygame.draw.rect(surface, shape.color, (sx + j*30, sy + i*30, 30, 30), 0)
 
    surface.blit(label, (sx + 10, sy- 30))
 
 
def draw_window(surface):
    surface.fill((0,0,0))
    # Title
    font = pygame.font.SysFont('comicsans', 60)
    label = font.render('Ai Avalanche', 1, (255,255,255))
 
    surface.blit(label, (top_left_x + play_width / 2 - (label.get_width() / 2), 30))
 
    for i in range(len(grid)):
        for j in range(len(grid[i])):
            pygame.draw.rect(surface, grid[i][j], (top_left_x + j* 30, top_left_y + i * 30, 30, 30), 0)
 
    draw_grid(surface, 20, 10)
    pygame.draw.rect(surface, (255, 0, 0), (top_left_x, top_left_y, play_width, play_height), 5)
 


def distance(player, piece):
    return numpy.sqrt((player.x - piece.x)**2 + (player.y - piece.y)**2)

def selection(genomes):       
    genomes = sorted(genomes, key=lambda genome: genome.fitness, reverse=True)        
    return genomes[:int(0.2 * len(genomes))]

def crossover(genomes):
    rate = 0.1
    offspring = []
    
    for ceva in range(int((population - len(genomes)) / 2)):
        parent1 = random.choice(genomes)
        parent2 = random.choice(genomes)
        
        child1 = AiPlayer()
        child1.pieceW = parent1.pieceW + parent2.pieceW * rate
        child1.rotationW = parent1.rotationW + parent2.rotationW * rate
        child1.distW = parent1.distW + parent2.distW * rate
        child1.distLeftW = parent1.distLeftW + parent2.distLeftW * rate
        child1.pieceXW = parent1.pieceXW + parent2.pieceXW * rate
        
        child2 = AiPlayer()
        child2.pieceW = parent2.pieceW + parent1.pieceW * rate
        child2.rotationW = parent2.rotationW + parent1.rotationW * rate
        child2.distLeftW = parent2.distLeftW + parent1.distLeftW * rate
        child2.pieceXW = parent2.pieceXW + parent1.pieceXW * rate
        
        offspring.append(child1)
        offspring.append(child2)
    
    genomes.extend(offspring)
    
    return genomes

def mutation(genomes):
    change = 15
    for genome in genomes:
        chance = random.uniform(0.0, 1.0)
        if chance <= 0.15:
            if chance <= 0.07:                
                genome.pieceW += genome.pieceW * chance * change
                genome.rotationW += genome.rotationW * chance * change
                genome.distW += genome.distW * chance * change
                genome.distLeftW += genome.distLeftW * chance * change
                genome.pieceXW += genome.pieceXW * chance * change
            else:
                genome.pieceW -= genome.pieceW * chance * change
                genome.rotationW -= genome.rotationW * chance * change
                genome.distW -= genome.distW * chance * change
                genome.distLeftW -= genome.distLeftW * chance * change
                genome.pieceXW -= genome.pieceXW * chance * change
            
    return genomes

def main():
    global grid
    score = 0
    contor_dead = 0
    dead_players = []
    
    genomePieces = [Piece(random.choice(range(1, 8)), 19, PLAYER, False) for _ in range (population)]
 
    locked_positions = {}
    grid = create_grid(locked_positions)
 
    change_piece = False
    run = True
    current_piece = get_shape()
    next_piece = get_shape()
    clock = pygame.time.Clock()
    fall_time = 0
    fall_speed = 0.04
    while run:        
        grid = create_grid(locked_positions)
        fall_time += clock.get_rawtime()
        clock.tick()
        
        # PIECE FALLING CODE
        if fall_time/1000 >= fall_speed:
            fall_time = 0
            current_piece.y += 1
            if not (valid_space(current_piece, grid)) and current_piece.y > 0:
                current_piece.y -= 1
                change_piece = True
 
        for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    run = False
                    with open('genomes.pkl', 'wb') as output:
                        pickle.dump(genomes, output, pickle.HIGHEST_PROTOCOL)
                    pygame.display.quit()
                    quit()
                    
        
        for playerPiece in genomePieces:
            index = genomePieces.index(playerPiece)
            output = numpy.tanh(
             genomes[index].pieceW * shapes.index(current_piece.shape)
            +  genomes[index].distW * distance(playerPiece, current_piece)
            +  genomes[index].rotationW * current_piece.rotation
            +  genomes[index].distLeftW * playerPiece.x
            +  genomes[index].pieceXW * current_piece.x)
            
            if output < -0.33:
                playerPiece.x -= 1
                if not valid_space(playerPiece, grid):
                    playerPiece.x += 1
            elif output > 0.33:
                playerPiece.x += 1
                if not valid_space(playerPiece, grid):
                    playerPiece.x -= 1
            
        shape_pos = convert_shape_format(current_piece)
 
        # add piece to the grid for drawing        
        for i in range(len(shape_pos)):
            x, y = shape_pos[i]
            if y > -1 and y <= 19:
                grid[y][x] = current_piece.color
                
        for playerPiece in genomePieces:           
            grid[19][playerPiece.x] = playerPiece.color
 
        # IF PIECE HIT GROUND
        if change_piece:
            score +=1
            del current_piece
            current_piece = next_piece
            next_piece = get_shape()
            change_piece = False             
 
        draw_window(win)
        pygame.display.update() 
        
        for playerPiece in genomePieces:            
            if check_lost(convert_shape_format(current_piece), convert_shape_format(playerPiece)):
                genomes[genomePieces.index(playerPiece)].fitness = score;
                contor_dead += 1
                dead_players.append(playerPiece)
                if not len(genomePieces) == 1:
                    grid[19][playerPiece.x] = (0, 0, 0)
                else:
                    grid[19][playerPiece.x] = current_piece.color
        
        for player in dead_players:
            del genomePieces[genomePieces.index(player)]
    
        dead_players = []
        # Check if user lost
        if contor_dead == population:
            draw_window(win)
            pygame.display.update()
            run = False
            contor_dead = 0
        
    print('Score: ', score)
    draw_text_middle("Game over", 40, (255,255,255), win)
    pygame.display.update()
    pygame.time.delay(1000)
    
    #in order to continue
    keyboard.press('a')
    keyboard.release('a')
 
def main_menu():
    global genomes
    try:
        with open('genomes.pkl', 'rb') as gen:
            genomes = pickle.load(gen)
    except:
        print('No previously saved genomes')
    run = True 
    generation = 0
    while run:
        win.fill((0,0,0))
        draw_text_middle('Press any key to begin.', 60, (255, 255, 255), win)
        pygame.display.update()
        
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                run = False
                with open('genomes.pkl', 'wb') as output:
                    pickle.dump(genomes, output, pickle.HIGHEST_PROTOCOL)
 
            if event.type == pygame.KEYDOWN:
                print('Generation: ', generation)
                main()
                champGenomes = selection(genomes)
                crossedGenomes = crossover(champGenomes)
                genomes = mutation(crossedGenomes)
                if len(genomes) < population:
                    genomes.append(AiPlayer())
                generation += 1
    pygame.quit()
 
 
win = pygame.display.set_mode((s_width, s_height))
pygame.display.set_caption('Ai Avalanche')
 
main_menu()  # start game