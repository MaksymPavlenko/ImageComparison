package entity;

import static java.lang.Math.sqrt;

public class Field {
    private int top;
    private int bot;
    private int left;
    private int right;

    public Field(int left, int right, int top, int bot) {
        this.top = top;
        this.bot = bot;
        this.left = left;
        this.right = right;
    }

    /**
     * @param x x coordinate of the point.
     * @param y y coordinate of the point
     * @return distance of the field to given point.
     */
    public double distance(int x, int y) {
        if ((x > right) && (y <= bot) && (y >= top)) {
            return x - right;
        } else if ((x > right) && (y < top)) {
            return sqrt((x - right) * (x - right) + (y - top) * (y - top));
        } else if ((y < top) && (x <= right) && (x >= left)) {
            return y - top;
        } else if ((y < top) && (x < left)) {
            return sqrt((x - left) * (x - left) + (y - top) * (y - top));
        } else if ((x < left) && (y <= bot) && (y >= top)) {
            return left - x;
        } else if ((x < left) && (y > bot)) {
            return sqrt((x - left) * (x - left) + (y - bot) * (y - bot));
        } else if ((y > bot) && (x <= right) && (x >= left)) {
            return y - bot;
        } else if ((x > right) && (y > bot)) {
            return sqrt((x - right) * (x - right) + (y - bot) * (y - bot));
        } else {
            return 0;
        }
    }

    /**
     * @param f another field distance to which need to be calculated.
     * @return distance to the closest point of given field.
     */
    public double distance(Field f) {
        if ((right < f.left) && ((top <= f.bot) || (bot >= f.top))) {
            return f.left - right;
        } else if ((right < f.left) && (top > f.bot)) {
            return sqrt((right - f.left) * (right - f.left) + (top - f.bot) * (top - f.bot));
        } else if ((top > f.bot) && ((left <= f.right) || (right >= f.left))) {
            return top - f.bot;
        } else if ((left > f.right) && (top > f.bot)) {
            return sqrt((left - f.right) * (left - f.right) + (top - f.bot) * (top - f.bot));
        } else if ((left > f.right) && ((top <= f.bot) || (bot >= f.top))) {
            return left - f.right;
        } else if ((left > f.right) && (bot < f.top)) {
            return sqrt((left - f.right) * (left - f.right) + (bot - f.top) * (bot - f.top));
        } else if ((bot < f.top) && ((left <= f.right) || (right >= f.left))) {
            return f.top - bot;
        } else if ((right < f.left) && (bot < f.top)) {
            return sqrt((right - f.left) * (right - f.left) + (bot - f.top) * (bot - f.top));
        } else {
            return 0;
        }
    }

    /**
     * Extends field in the direction of the point, so the field could hit the point.
     *
     * @param x x coordinate of the point.
     * @param y y coordinate of the point.
     */
    public void extend(int x, int y) {
        if (left > x) {
            left = x;
        }
        if (right < x) {
            right = x;
        }
        if (top > y) {
            top = y;
        }
        if (bot < y) {
            bot = y;
        }
    }

    /**
     * Extends field in the direction of another, so the field could completely overlap another.
     *
     * @param f field which should be hit.
     */
    public void extend(Field f) {
        if (left > f.left) {
            left = f.left;
        }
        if (right < f.right) {
            right = f.right;
        }
        if (top > f.top) {
            top = f.top;
        }
        if (bot < f.bot) {
            bot = f.bot;
        }
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBot() {
        return bot;
    }

    public void setBot(int bot) {
        this.bot = bot;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Field{" +
                "top=" + top +
                ", bot=" + bot +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}