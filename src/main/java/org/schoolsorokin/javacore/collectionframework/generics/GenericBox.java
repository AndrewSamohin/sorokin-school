package org.schoolsorokin.javacore.collectionframework.generics;

public class GenericBox<T> {
    private T content;

    public GenericBox(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean isEqual(GenericBox<T> otherBox) {
        return this.content.equals(otherBox.getContent());
    }

    public void swap(GenericBox<T> otherBox) {
        T temp = this.content;
        this.content = otherBox.content;
        otherBox.content = temp;
    }
}
