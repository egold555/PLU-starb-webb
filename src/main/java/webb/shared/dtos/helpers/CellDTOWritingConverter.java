package webb.shared.dtos.helpers;

import com.mongodb.BasicDBList;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;

import org.springframework.data.convert.WritingConverter;
import webb.shared.dtos.puzzle.CellDTO;

@WritingConverter
public class CellDTOWritingConverter implements Converter<CellDTO, DBObject> {
    @Override
    public DBObject convert(CellDTO cellDTO) {
        BasicDBList list = new BasicDBList();
        list.add(cellDTO.getRow());
        list.add(cellDTO.getCol());
        return list;
    }
}
