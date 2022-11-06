package com.shpp.p2p.cs.ydanylenko.breakout;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;


/**
 * Class Breakout realizes a classical arcade "Breakout" gameplay that allows
 * move by cursor the graphic element called the "paddle" in order to bounce
 * off the graphic element called the "ball".
 * The hit from the paddle ball then follows the field and collides with a
 * wall of bricks. When the ball touches a brick, the ball destroys this brick
 * and changes the moving direction. The game ends with the player's victory
 * when the player destroys the entire wall or with a player's failure of exhaustion
 * of game attempts.
 */

public class Breakout extends WindowProgram {
    /* Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /* Dimensions of game board (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    /* Width of a brick */
    private static final int HEIGHT = APPLICATION_HEIGHT;
    /* Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;
    /* Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 50;
    /* Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 10;
    /* Number of rows of bricks */
    private static final int NBRICK_ROWS = 10;
    /* Separation between bricks */
    private static final int BRICK_SEP = 4;
    /* Height of a brick */
    private static final int BRICK_HEIGHT = 8;
    /* Radius of the ball in pixels */
    /* Width of a brick */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;
    private static final int BALL_RADIUS = 5;
    /* Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 100;
    /* Number of turns */
    private static final int NTURNS = 3;
    /* Velocity of ball movement */
    private static final int GAMESPEED = 30;
    /* Paddle board graphic object */
    private static GRect paddle;
    /* Ball graphic object */
    private static GOval ball;
    /* x and y velocity directions components respectively */
    private double vx, vy;
    /* Counter of failed game tries */
    private int gameCounter;
    /* Counter of removed bricks */
    private int brickCounter;

    public void run() {
        addMouseListeners();
        playGame();
    }

    /* Starts a game play and keeps it active till the user wins or fails. */
    private void playGame() {
        addBricksRow();
        addPaddle();
        addBall();
        /* Starts game play after user clicks the mouse */
        waitForClick();
        getBallMove();
    }

    /* Builds wall of bricks using the given constants. */
    private void addBricksRow() {
        /* A single brick row width along the x direction */
        double rowWidth = (NBRICKS_PER_ROW) * (BRICK_WIDTH + BRICK_SEP) - BRICK_SEP;
        /* A 2 dimensional array of bricks wall */
        GRect[][] brickRows = new GRect[NBRICKS_PER_ROW][NBRICK_ROWS];
        /* Fill i component of 2 dimensional array to draw a single row wide */
        for (int i = 0; i < NBRICKS_PER_ROW; i++) {
            /* Fill j component of 2 dimensional array draw the wall according to its height */
            for (int j = 0; j < NBRICK_ROWS; j++) {
                brickRows[i][j] =
                        new GRect((WIDTH + rowWidth) / 2 - (i + 1) * (BRICK_WIDTH + BRICK_SEP) + BRICK_SEP,
                                BRICK_Y_OFFSET + j * (BRICK_HEIGHT + BRICK_SEP),
                                BRICK_WIDTH,
                                BRICK_HEIGHT);
                brickRows[i][j].setFilled(true);
                brickRows[i][j].setColor(setBrickColor(j));
                brickRows[i][j].setFillColor(setBrickColor(j));
                add(brickRows[i][j]);
            }
        }
    }

    /* Creates a paddle as a filled black rectangle object.*/
    private void addPaddle() {
        paddle = new GRect((WIDTH - PADDLE_WIDTH) / 2d,
                HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT, PADDLE_WIDTH,
                PADDLE_HEIGHT);
        paddle.setFilled(true);
        add(paddle);
    }

    /* Creates a ball as a filled black circle. */
    private void addBall() {
        /* Ball diameter */
        double ballDiameter = 2 * BALL_RADIUS;
        ball = new GOval((WIDTH / 2d) - BALL_RADIUS,
                (HEIGHT / 2d) - BALL_RADIUS, ballDiameter,
                ballDiameter);
        ball.setFilled(true);
        add(ball);
    }

    /* Makes ball move on the field and interact with objects on the canvas. */
    private void getBallMove() {
        setBallVelocity();
        /* The finite motion cycle that ends with failed games attempts or
        destroying the brick wall */
        while (true) {
            ball.move(vx, vy);
            pause(GAMESPEED);
            bounceBallOffWall();
            bounceBallOffPaddle();
            destroyWallElement();
            if (isWallDestroyed()) {
                showEndGameMessage();
                break;
            }
            if (isBallOutCanvas()) {
                gameCounter++;
                showEndGameMessage();
                startNewRound();
                if (areAttemptsEnded()) {
                    break;
                }
            }
        }
    }

    /* Sets the velocity x and y component */
    private void setBallVelocity() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5))
            /* x component of ball speed vector, in pixels*/
            vx = -vx;
        /* y component of ball speed vector, in pixels */
        vy = 3;
    }

    /* Checks if the ball bounces off the wall and change its direction as a
    result. */
    private void bounceBallOffWall() {
        /* The ball diameter */
        if (isBallBounceOffWall()) {
            vx = -vx;
        }
        if (isBallFallCanvasDown()) {
            vy = -vy;
        }
    }

    /**
     * Checks if the ball is bounced by the paddle and changes ball moving
     * direction in a result.
     */
    private void bounceBallOffPaddle() {
        if (isBounceOffPaddleHead()) {
            vy = -vy;
        }
    }

    /**
     * Removes brick(s) after ball touched them after bouncing for the
     * paddle. And also change the ball movement direction to the opposite.
     */
    private int destroyWallElement() {
        GObject collider = getCollidingObject();
        if (collider != paddle && collider != null) {
            if (isBallBounceOffBrickCorner()) {
                vx = -vx;
            }
            vy = -vy;
            remove(collider);
            brickCounter++;
        }
        return brickCounter;
    }

    /**
     * Checks if the wall of bricks is fully destroyed.
     *
     * @return "true" if wall of bricks is fully destroyed.
     */
    private boolean isWallDestroyed() {
        return brickCounter >= NBRICK_ROWS * NBRICKS_PER_ROW;
    }

    /**
     * Informs user about game round final and shows the number of a left
     * game attempts.
     */
    private void showEndGameMessage() {
        boolean wallDestroyed = isWallDestroyed();
        GLabel endMessage;
        if (wallDestroyed) {
            String messageOfWallDestroy =
                    "Game Over. You destroyed all the " + brickCounter +
                            " bricks";
            endMessage = new GLabel(messageOfWallDestroy, 0, 0);
        } else {
            String messageOfEndedAttempts = "Game Over. You have " + (NTURNS - gameCounter) +
                    " attempt(s) " +
                    "more";
            endMessage = new GLabel(messageOfEndedAttempts, 0, 0);
        }
        endMessage.setFont("Veranda-17");
        endMessage.setLocation((WIDTH / 2d) - endMessage.getBounds().getWidth() / 2, 0);
        add(endMessage);
        /* Pause time of animation */
        double pauseTime = 100;
        /* y component of GLabel object movement */
        double vy = 10;
        while (true) {
            endMessage.move(0, vy);
            pause(pauseTime);
            if (endMessage.getY() > HEIGHT / 2d) {
                break;
            }
        }
        remove(endMessage);
    }

    /**
     * Checks if ball isn't bounce by player and the ball falls out the
     * play field down.
     *
     * @return - "true" if ball moved out the play field.
     */
    private boolean isBallOutCanvas() {
        return ball.getY() >= HEIGHT;
    }

    /**
     * Starts new play round (attempt) with placing a new ball in the center
     * of play field.
     */
    private void startNewRound() {
        remove(ball);
        /* Pause between rounds */
        double pauseBetweenRoundTime = 3000;
        pause(pauseBetweenRoundTime);
        addBall();
        waitForClick();
    }

    /**
     * Checks if user exhausted all attempts to play game.
     *
     * @return - "true" if all attempts are exhausted.
     */
    private boolean areAttemptsEnded() {
        return gameCounter == NTURNS;
    }

    /**
     * Checks if ball bounces off the canvas sides set by application size
     * constants.
     *
     * @return - "true" if ball bounces off left or right canvas sides.
     */
    private boolean isBallBounceOffWall() {
        double ballDiameter = 2 * BALL_RADIUS;
        return (ball.getX() <= 0) || (ball.getX() >= WIDTH - ballDiameter);
    }

    /**
     * Checks if ball falls canvas down if ball isn't bounced off by paddle.
     *
     * @return - "true" if ball falls canvas down.
     */
    private boolean isBallFallCanvasDown() {
        return ball.getY() <= 0;
    }

    /**
     * Checks if the ball bounces off the paddle head side.
     *
     * @return - "true" if ball bounces off the paddle's head.
     */
    private boolean isBounceOffPaddleHead() {
        GObject collider = getCollidingObject();
        return collider == paddle && vy > 0;
    }

    /**
     * Checks if ball bounces off the brick's corner and changes ball
     * x-coordinate velocity sign.
     * @return
     */
    private boolean isBallBounceOffBrickCorner() {
        GObject collider = getCollidingObject();;
        return ball.getX() <= collider.getX() && (ball.getY() >= collider.getY() ||
                ball.getX() <= collider.getX() - BRICK_WIDTH);
    }

    /**
     * Sets the color of brick rows.
     *
     * @param rowNum - the number of row counted from wall's up to down.
     * @return - color of the row.
     */
    private Color setBrickColor(int rowNum) {
        Color brickRowColor = null;
        int colorNum;
        if (NBRICK_ROWS % 2 == 0) {
            colorNum = NBRICK_ROWS / 2;
        } else {
            colorNum = (NBRICK_ROWS / 2) + 1;
        }
        Color[] brickColor = new Color[10];
        brickColor[0] = brickColor[1] = Color.RED;
        brickColor[2] = brickColor[3] = Color.ORANGE;
        brickColor[4] = brickColor[5] = Color.YELLOW;
        brickColor[6] = brickColor[7] = Color.GREEN;
        brickColor[8] = brickColor[9] = Color.CYAN;
        for (int k = 0; k < colorNum; k++) {
            if (rowNum == 2 * k || rowNum == 2 * k + 1) {
                /* Passes the remainder of a number divided by 10 (array
                length in this case).
                That is, 10 / 10 = 0; 11 / 10 = 1; 15 / 10 = 5; 37 / 10 = 7;
                458 / 10 = 8, etc*/
                rowNum = Math.floorMod(rowNum, brickColor.length);
                brickRowColor = brickColor[rowNum];
            }
        }
        return brickRowColor;
    }

    /**
     * Checks if the ball touches (in the bounding rectangle vertices) the
     * canvas objects (paddle or bricks).
     *
     * @return the object if the ball touched it or null if the ball didn't
     * touch it.
     */
    private GObject getCollidingObject() {
        GObject obj = null;
        if (getElementAt(ball.getX(), ball.getY()) != null) {
            obj = getElementAt(ball.getX(), ball.getY());
        } else if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != null) {
            obj = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
        } else if (getElementAt(ball.getX() + 2 * BALL_RADIUS,
                ball.getY() + 2 * BALL_RADIUS) != null) {
            obj = getElementAt(ball.getX() + 2 * BALL_RADIUS,
                    ball.getY() + 2 * BALL_RADIUS);
        } else if (getElementAt(ball.getX(),
                ball.getY() + 2 * BALL_RADIUS) != null) {
            obj = getElementAt(ball.getX(),
                    ball.getY() + 2 * BALL_RADIUS);
        }
        return obj;
    }

    /**
     * Moves the paddle with the mouse cursor along the x-axis.
     *
     * @param l - an event occurring in a result of mouse moving
     */
    public void mouseMoved(MouseEvent l) {
        paddle.setLocation(l.getX() - PADDLE_WIDTH / 2d,
                HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
        if (l.getX() < PADDLE_WIDTH / 2) {
            paddle.setLocation(0, HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
        }
        if (l.getX() > WIDTH - PADDLE_WIDTH / 2) {
            paddle.setLocation(getWidth() - PADDLE_WIDTH, HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
        }
    }

}