package com.demo.pokepb.mapper;

import com.demo.pokepb.entity.Pokemon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PokemonMapperTest {
    /*
        @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)と
        することでアプリケーションで設定されているDBを使用する。
        初期設定として
        ・151匹が登録されていること
        ・登録されているデータに対して更新できること を確認することを目的としたテスト。
     */

    @Autowired
    private PokemonMapper pokemonMapper;

    public static final String[] ARRAY_POKEMON_NAME = {
            "フシギダネ", "フシギソウ", "フシギバナ", "ヒトカゲ", "リザード", "リザードン", "ゼニガメ", "カメール", "カメックス", "キャタピー",
            "トランセル", "バタフリー", "ビードル", "コクーン", "スピアー", "ポッポ", "ピジョン", "ピジョット", "コラッタ", "ラッタ",
            "オニスズメ", "オニドリル", "アーボ", "アーボック", "ピカチュウ", "ライチュウ", "サンド", "サンドパン", "ニドラン♀", "ニドリーナ",
            "ニドクイン", "ニドラン♂", "ニドリーノ", "ニドキング", "ピッピ", "ピクシー", "ロコン", "キュウコン", "プリン", "プクリン",
            "ズバット", "ゴルバット", "ナゾノクサ", "クサイハナ", "ラフレシア", "パラス", "パラセクト", "コンパン", "モルフォン", "ディグダ",
            "ダグトリオ", "ニャース", "ペルシアン", "コダック", "ゴルダック", "マンキー", "オコリザル", "ガーディ", "ウインディ", "ニョロモ",
            "ニョロゾ", "ニョロボン", "ケーシィ", "ユンゲラー", "フーディン", "ワンリキー", "ゴーリキー", "カイリキー", "マダツボミ", "ウツドン",
            "ウツボット", "メノクラゲ", "ドククラゲ", "イシツブテ", "ゴローン", "ゴローニャ", "ポニータ", "ギャロップ", "ヤドン", "ヤドラン",
            "コイル", "レアコイル", "カモネギ", "ドードー", "ドードリオ", "パウワウ", "ジュゴン", "ベトベター", "ベトベトン", "シェルダー",
            "パルシェン", "ゴース", "ゴースト", "ゲンガー", "イワーク", "スリープ", "スリーパー", "クラブ", "キングラー", "ビリリダマ",
            "マルマイン", "タマタマ", "ナッシー", "カラカラ", "ガラガラ", "サワムラー", "エビワラー", "ベロリンガ", "ドガース", "マタドガス",
            "サイホーン", "サイドン", "ラッキー", "モンジャラ", "ガルーラ", "タッツー", "シードラ", "トサキント", "アズマオウ", "ヒトデマン",
            "スターミー", "バリヤード", "ストライク", "ルージュラ", "エレブー", "ブーバー", "カイロス", "ケンタロス", "コイキング", "ギャラドス",
            "ラプラス", "メタモン", "イーブイ", "シャワーズ", "サンダース", "ブースター", "ポリゴン", "オムナイト", "オムスター", "カブト",
            "カブトプス", "プテラ", "カビゴン", "フリーザー", "サンダー", "ファイヤー", "ミニリュウ", "ハクリュー", "カイリュー", "ミュウツー",
            "ミュウ"
    };
    public static final String[] ARRAY_POKEMON_TYPE1 = {
            "くさ", "くさ", "くさ", "ほのお", "ほのお", "ほのお", "みず", "みず", "みず", "むし",
            "むし", "むし", "むし", "むし", "むし", "ノーマル", "ノーマル", "ノーマル", "ノーマル", "ノーマル",
            "ノーマル", "ノーマル", "どく", "どく", "でんき", "でんき", "じめん", "じめん", "どく", "どく",
            "どく", "どく", "どく", "どく", "ノーマル", "ノーマル", "ほのお", "ほのお", "ノーマル", "ノーマル",
            "どく", "どく", "くさ", "くさ", "くさ", "むし", "むし", "むし", "むし", "じめん",
            "じめん", "ノーマル", "ノーマル", "みず", "みず", "かくとう", "かくとう", "ほのお", "ほのお", "みず",
            "みず", "みず", "エスパー", "エスパー", "エスパー", "かくとう", "かくとう", "かくとう", "くさ", "くさ",
            "くさ", "みず", "みず", "いわ", "いわ", "いわ", "ほのお", "ほのお", "みず", "みず",
            "でんき", "でんき", "ノーマル", "ノーマル", "ノーマル", "みず", "みず", "どく", "どく", "みず",
            "みず", "ゴースト", "ゴースト", "ゴースト", "いわ", "エスパー", "エスパー", "みず", "みず", "でんき",
            "でんき", "くさ", "くさ", "じめん", "じめん", "かくとう", "かくとう", "ノーマル", "どく", "どく",
            "いわ", "いわ", "ノーマル", "くさ", "ノーマル", "みず", "みず", "みず", "みず", "みず",
            "みず", "エスパー", "むし", "こおり", "でんき", "ほのお", "むし", "ノーマル", "みず", "みず",
            "みず", "ノーマル", "ノーマル", "みず", "でんき", "ほのお", "ノーマル", "みず", "みず", "みず",
            "みず", "いわ", "ノーマル", "こおり", "でんき", "ほのお", "ドラゴン", "ドラゴン", "ドラゴン", "エスパー",
            "エスパー"
    };
    public static final String[] ARRAY_POKEMON_TYPE2 = {
            "どく", "どく", "どく", "", "", "ひこう", "", "", "", "",
            "", "ひこう", "どく", "どく", "どく", "ひこう", "ひこう", "ひこう", "", "",
            "ひこう", "ひこう", "", "", "", "", "", "", "", "",
            "じめん", "", "", "じめん", "", "", "", "", "", "",
            "ひこう", "ひこう", "どく", "どく", "どく", "くさ", "くさ", "どく", "どく", "",
            "", "", "", "", "", "", "", "", "", "",
            "", "かくとう", "", "", "", "", "", "", "どく", "どく",
            "どく", "どく", "どく", "じめん", "じめん", "じめん", "", "", "エスパー", "エスパー",
            "", "", "ひこう", "ひこう", "ひこう", "", "こおり", "", "", "",
            "こおり", "どく", "どく", "どく", "じめん", "", "", "", "", "",
            "", "", "エスパー", "", "", "", "", "", "", "",
            "じめん", "じめん", "", "", "", "", "", "", "", "",
            "エスパー", "", "ひこう", "", "", "", "", "", "", "ひこう",
            "こおり", "", "", "", "", "", "", "いわ", "いわ", "いわ",
            "いわ", "ひこう", "", "ひこう", "ひこう", "ひこう", "", "", "ひこう", "",
            ""
    };
    public static final String[][] ARRAY_POKEMON_UPDATE = {
            { "タイプ1", "タイプ2" },
            { "", "タイプ1" },
            { "タイプ1", "" },
            { "", "" }
    };


    @Test
    @DisplayName("findAllPokemonメソッドで取得")
    public void Obtained_by_findAllPokemon_method() {
        List<Pokemon> pokemonList = pokemonMapper.findAllPokemon();
        assertEquals(151, pokemonList.size());

        for(int i = 0; i < 151; i++) {
            Pokemon pokemon = pokemonMapper.findIdPokemon(i+1);
            assertEquals(pokemon.getName(), ARRAY_POKEMON_NAME[i]);
            assertEquals(pokemon.getType1(), ARRAY_POKEMON_TYPE1[i]);
            assertEquals(pokemon.getType2(), ARRAY_POKEMON_TYPE2[i]);
        }
    }

    @Test
    @DisplayName("findIdPokemonメソッドで取得")
    public void Obtained_by_findIdPokemon_method() {
        for(int i = 0; i < 151; i++) {
            Pokemon pokemon = pokemonMapper.findIdPokemon(i+1);
            assertEquals(pokemon.getName(), ARRAY_POKEMON_NAME[i]);
            assertEquals(pokemon.getType1(), ARRAY_POKEMON_TYPE1[i]);
            assertEquals(pokemon.getType2(), ARRAY_POKEMON_TYPE2[i]);
        }
    }

    @Test
    @Transactional
    @DisplayName("updateIdPokemonメソッドで取得")
    public void Obtained_by_updateIdPokemon_method() {
        for(int i = 0; i < 151; i++) {
            int count = pokemonMapper.updateIdPokemon(i+1, ARRAY_POKEMON_UPDATE[(i+4)%4][0], ARRAY_POKEMON_UPDATE[(i+4)%4][1] );
            Pokemon pokemon = pokemonMapper.findIdPokemon(i+1);
            assertEquals(pokemon.getType1(), ARRAY_POKEMON_UPDATE[(i+4)%4][0]);
            assertEquals(pokemon.getType2(), ARRAY_POKEMON_UPDATE[(i+4)%4][1]);
            assertEquals(count, 1);
        }
    }
}
