package com.zmc.common.vo;

import java.io.Serializable;

public class Node implements Serializable {
        Long id;
        Boolean checked;
        public Node(){}

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setChecked(Boolean checked) {
            this.checked = checked;
        }

        public Boolean getChecked() {
            return checked;
        }
    }