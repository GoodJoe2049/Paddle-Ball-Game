# Paddle-Ball-Game
Classic Paddle ball game with a twist made in Java with native javax.swing graphics
.
.
.
-Controls-
Cursor: used for paddle tracking left/right (left/right arrow keys may also be used, but are not recommended due to accuracy and speed).
M1/M2/Enter: used to restart/continue to next level when win/loss condition occurs.
Space Bar: used to pause/play

-Gameplay-
-The game has a semi winning condition upon beating the boss in level 9, after which the game continues indefinitely.
-Breaking bricks earns the player souls. Losing, which constitutes the ball going bellow the paddle, costs the player souls. If the player loses all souls it's game over.
-Starts with a small number of bricks which increase every level. The speed of the ball increases with difficulty indefinitely, its size decreases with difficulty until it reaches its cap in level 5.
-There are 4 power-ups/bonuses which may proc randomly upon hitting a brick as follows:
  1. Big Ball: the ball becomes bigger and purple. Loses size every time it hits a brick for a total of 5 hits until the effect wears off. Proc chance: 1/25*
  2. +5 All Bonus: All bricks become green and count for 5 souls instead of 1. Effect wears off when the ball hits the paddle 5 times. Proc chance: 1/25
  3. +5 single Bonus: Any given brick has a chance of counting for 5 souls instead of 1 upon hit. Proc chance: 1/15
  4. Fire Ball: the ball lights on fire and burns through bricks without bouncing off them. Effect wears off when the ball hits the paddle 5 times: Proc chance: 1/25*
    
    *Big Ball and Fire ball cannot proc simultaneously, only one may be active at a time. Thus, they share the same random variable.*
    
-There
