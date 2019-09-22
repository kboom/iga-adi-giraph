package edu.agh.iga.adi.giraph.core;

import java.io.Serializable;

public final class Mesh implements Serializable {

    private double resolutionX;

    private double resolutionY;

    private int elementsX;

    private int elementsY;

    private int splineOrder = 2;

    private int dofsX;

    private int dofsY;

    private Mesh() {
    }

    public static MeshBuilder aMesh() {
        return new MeshBuilder();
    }

    public double getDx() {
        return resolutionX / elementsX;
    }

    public double getDy() {
        return resolutionY / elementsY;
    }

    public int getElementsY() {
        return elementsY;
    }

    public int getDofsX() {
        return dofsX;
    }

    public int getDofsY() {
        return dofsY;
    }

    public static class MeshBuilder {

        private Mesh mesh = new Mesh();

        public MeshBuilder withElements(int elements) {
            withElementsX(elements);
            withElementsY(elements);
            return this;
        }

        public MeshBuilder withElementsX(int elementsX) {
            mesh.elementsX = elementsX;
            return this;
        }

        public MeshBuilder withElementsY(int elementsY) {
            mesh.elementsY = elementsY;
            return this;
        }

        public Mesh build() {
            if (mesh.resolutionY == 0) {
                mesh.resolutionY = mesh.elementsY;
            }
            if (mesh.resolutionX == 0) {
                mesh.resolutionX = mesh.elementsX;
            }
            mesh.dofsX = mesh.elementsX + mesh.splineOrder;
            mesh.dofsY = mesh.elementsY + mesh.splineOrder;
            return mesh;
        }

    }

}
