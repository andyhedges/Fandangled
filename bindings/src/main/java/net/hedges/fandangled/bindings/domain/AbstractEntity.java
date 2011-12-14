package net.hedges.fandangled.bindings.domain;

/**
 * Created by IntelliJ IDEA.
 * User: andy
 * Date: 14/12/11
 * Time: 09:42
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractEntity {

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
