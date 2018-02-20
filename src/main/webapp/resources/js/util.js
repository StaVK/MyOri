function addProductInOrder(orderId,prodId) {
    // TODO: Читает только первый тег vol в таблице, а остальные нет.
    var volume;
    volume=document.getElementById(prodId).value;
    // создаем форму с нужными action и method
    var form = document.createElement("form"), tmp;
    // form.style.display='none';
    form.method = "get";
    form.action = "op/addProductInOrder";
    // заполняем форму скрытыми полями, беря пары name:value из хеша what
    tmp = document.createElement("input");
    tmp.type = "hidden";
    tmp.name= "orderId";
    tmp.value=orderId;
    form.appendChild(tmp);

    tmp = document.createElement("input");
    tmp.type = "hidden";
    tmp.name= "prodId";
    tmp.value=prodId;
    form.appendChild(tmp);

    tmp = document.createElement("input");
    tmp.type = "hidden";
    tmp.name= "volume";
    tmp.value=volume;
    form.appendChild(tmp);

    // вставляем форму на страницу и посылаем
    document.body.appendChild(form);
    form.submit();
}