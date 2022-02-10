# Paddle-Ball-Game
Classic Paddle ball game with a twist made in Java with native javax.swing graphics
.
.
.

-CONTROLS-

Cursor: used for paddle tracking left/right (left/right arrow keys may also be used, but are not recommended due to accuracy and speed).

M1/M2/Enter: used to restart/continue to next level when win/loss condition occurs.

Space Bar: used to pause/play

-GAMEPLAY-

-The game has a semi winning condition upon beating the boss in circle (level) 9, after which the game continues indefinitely.

-Breaking bricks earns the player souls. Losing, which constitutes the ball going bellow the paddle, costs the player souls. If the player loses all souls it's game over.

-Starts with a small number of bricks which increase every circle. The speed of the ball increases with difficulty indefinitely, its size decreases with difficulty until it reaches its cap in circle 5.

-There are 4 power-ups/bonuses which may proc randomly upon hitting a brick as follows:

  1. Big Ball: the ball becomes bigger and purple. Loses size every time it hits a brick for a total of 5 hits until the effect wears off. Proc chance: 1/25*
  2. +5 All Bonus: All bricks become green and count for 5 souls instead of 1. Effect wears off when the ball hits the paddle 5 times. Proc chance: 1/25
  3. +5 single Bonus: Any given brick has a chance of counting for 5 souls instead of 1 upon hit. Proc chance: 1/15
  4. Fire Ball: the ball lights on fire and burns through bricks without bouncing off them. Effect wears off when the ball hits the paddle 5 times: Proc chance: 1/25*
    
    * Big Ball and Fire ball cannot proc simultaneously, only one may be active at a time. Thus, they share the same random variable. *
    
-There are 2 factors that affect soul loss:

  1. Standard soul loss: when the ball falls bellow the paddle, the player loses souls according to the following equation: Soul loss = (current circle * 10) + (current circle * loss streak). Loss streak refers to the amount of times the player has lost in the current level, thus winning the level resets this value to 0. For example, if player is at circle 3 and has not lost previously, the equation is (3 * 10) + (3 * 0) = 30. If the player loses in this circle again, then it's (3 * 10) + (3 * 1) = 33 and so on.

  2. Cursed Chance soul loss: when the player loses, there is a chance that the player will lose a substantially greater amount of souls. This chance is: current circle / 100, which means the proc percentage is the same as the current circle (if current circle is 3, then proc chance is 3/100). This means that circles 100+ guarantee curse. The exact soul loss is determined by the equation: Soul loss = (current circle * 10) + (current circle * loss streak) + (current circle * 50). This is the same as standard soul loss with (current circle * 50) added. Using the first example, we have (3 * 10) + (3 * 0) + (3 * 50) = 180, which would be 183 if the player had lost once previously.
