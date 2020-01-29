package structural.composit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

interface SomeNeurons extends Iterable<Neuron> {

    default void connectTo(SomeNeurons other) {
        if (this == other) return;

        for (Neuron from : this) {
            for (Neuron to : other) {
                from.out.add(to);
                to.in.add(from);
            }
        }
    }
}

public class Neuron implements SomeNeurons {

    public ArrayList<Neuron> in, out;

    @Override
    public Iterator<Neuron> iterator() {
        return Collections.singleton(this).iterator();
    }

    @Override
    public void forEach(Consumer<? super Neuron> action) {
        action.accept(this);
    }

    @Override
    public Spliterator<Neuron> spliterator() {
        return Collections.singleton(this).spliterator();
    }

//    public void connectTo(Neuron other) {
//        out.add(other);
//        other.in.add(this);
//    }
}

class NeuronLayer extends ArrayList<Neuron> implements SomeNeurons{}

class DemoNeuron {

    public static void main(String[] args) {
        final Neuron neuron = new Neuron();
        final Neuron neuron1 = new Neuron();

        final NeuronLayer layer = new NeuronLayer();
        final NeuronLayer layer1 = new NeuronLayer();

        neuron.connectTo(neuron1);
        neuron.connectTo(layer);
        layer.connectTo(neuron);
        layer.connectTo(layer1);
    }

}