package com.tl.utils.map3d;

public class Point3DUtils {

    public static double distance(Point3D p1, Point3D p2) {
        double dx = p1.x() - p2.x();
        double dy = p1.y() - p2.y();
        double dz = p1.z() - p2.z();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}
