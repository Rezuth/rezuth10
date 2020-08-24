import chess
import chess.svg
import os

from PyQt5.QtCore import pyqtSlot, Qt, QTimer
from PyQt5.QtSvg import QSvgWidget
from PyQt5.QtWidgets import QApplication, QWidget

from stockfish import Stockfish


class MainWindow(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("PinkFish")
        self.setGeometry(400, 400, 1300, 1300)
        self.widgetSvg = QSvgWidget(self)
        self.widgetSvg.setGeometry(20, 20, 1200, 1200)
        self.chessboard = chess.Board()
        self.chessboardSvg = chess.svg.board(self.chessboard).encode("UTF-8")

        self.checkThreadTimer = QTimer(self)
        self.checkThreadTimer.setInterval(1000)
        self.checkThreadTimer.start()
        self.checkThreadTimer.timeout.connect(self.play)

    @pyqtSlot(QWidget)
    def mousePressEvent(self, event):
        if event.buttons() == Qt.LeftButton:
            self.update()

    @pyqtSlot(QWidget)
    def paintEvent(self, event):
        self.chessboardSvg = chess.svg.board(self.chessboard).encode("UTF-8")
        self.widgetSvg.load(self.chessboardSvg)

    def play(self):
        dirname = os.path.dirname(__file__)
        filename = os.path.join(dirname, 'stockfish-9-win\Windows\stockfish_9_x64')
        stockfish = Stockfish(filename)
        stockfish.set_skill_level(15)
        if not self.chessboard.is_game_over() and not self.chessboard.is_stalemate():
            board = self.chessboard.fen()
            stockfish.set_fen_position(board)
            ai_move = stockfish.get_best_move()
            move = chess.Move.from_uci(ai_move)
            print(move)
            self.chessboard.push(move)
            self.update()


if __name__ == "__main__":
    app = QApplication([])
    window = MainWindow()
    window.show()
    app.exec()