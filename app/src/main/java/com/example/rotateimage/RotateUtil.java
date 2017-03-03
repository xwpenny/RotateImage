package com.example.rotateimage;

/**
 * Created by xianwei on 17/3/3.
 */

public class RotateUtil {
    public static float computeAngle(float centerX, float centerY, float startX, float startY, float endX, float endY) {
        double ab = Math.sqrt((centerX - startX) * (centerX - startX) + (centerY - startY) * (centerY - startY));
        double ac = Math.sqrt((centerX - endX) * (centerX - endX) + (centerY - endY) * (centerY - endY));
        double bc = Math.sqrt((endX - startX) * (endX - startX) + (endY - startY) * (endY - startY));
        double cos = (ab * ab + ac * ac - bc * bc) / 2 / ab / ac;
        if (cos >= 1) {
            return 0;
        }
        return (float) (Math.acos(cos) / Math.PI * 180);
    }

    public static boolean isClockWise(float centerX, float centerY, float startX, float startY, float endX, float endY) {
        float endDY = (endX - centerX) / (startX - centerX) * (startY - centerY) + centerY;
        if (startX > centerX) {
            if (endY > endDY) {
                return true;
            } else {
                return false;
            }
        }
        if (startX < centerX) {
            if (endY > endDY) {
                return false;
            } else {
                return true;
            }
        }
        if (startX == centerX) {
            if (startY < centerY) {
                if (endX > centerX) {
                    return true;
                } else {
                    return false;
                }
            }
            if (startY > centerY) {
                if (endX < centerX) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
