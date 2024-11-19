package com.example.Kanban.Entity.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskPriority {
    ALTA("Alta"),
    BAIXA("Baixa"),
    MEDIA("Media");

    private String descricao;

    TaskPriority(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TaskPriority fromDescricao(String descricao) {
        for (TaskPriority prioridade : TaskPriority.values()) {
            if (prioridade.getDescricao().equalsIgnoreCase(descricao)) {
                return prioridade;
            }
        }
        throw new IllegalArgumentException("Unknown priority: " + descricao);
    }
}
